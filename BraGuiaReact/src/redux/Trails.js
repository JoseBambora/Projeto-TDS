import realm from "./DB";
import { AddPinDB } from "./Pins";


const edgesConverter = (edges) => {
  return edges.map(edge => {
    return {
      id: edge.id,
      edge_start: edge.edge_start.id,
      edge_end: edge.edge_end.id,
      edge_transport: edge.edge_transport,
      edge_duration: edge.edge_duration,
      edge_desc: edge.edge_desc,
      edge_trail: edge.edge_trail,
    };
  });
}

const Insert = (trail) => {
  realm.write(() => {
    realm.create('Trail', trail)
    console.log('Adicionou o trail ' + trail.id)
  })
}

const Update = (trail) => {
  realm.write(() => {
    realm.create('Trail', trail,'Update')
    console.log('Deu update ao trail ' + trail.id)
  })
}

export const AddTrailDB = (trail) => {
  trail.edges.forEach(edge => {
    AddPinDB(edge.edge_start)
    AddPinDB(edge.edge_end)
  })
  trail.edges = edgesConverter(trail.edges)
  if (GetTrailDB(trail.id))
    Update(trail)
  else 
    Insert(trail)
}

export const AddTrailsDB = (trails) => trails.forEach(t => AddTrailDB(t))
export const GetTrailsDB = () => Array.from(realm.objects('Trail'));
export const GetTrailDB = (id) => realm.objectForPrimaryKey('Trail', id);