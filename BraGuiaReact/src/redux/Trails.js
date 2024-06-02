import realm from "./DB";
import { AddPinDB, GetPinDB } from "./Pins";
import { deepCopy } from "./Utils";

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

const AddEdges = (trail) => {
  if(trail) {
    trail.edges = trail.edges.map((edge) => {
      edge.edge_start = GetPinDB(edge.edge_start)
      edge.edge_end = GetPinDB(edge.edge_end)
      return edge
    })
  }
  return trail
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
  return trail.id
}

export const AddTrailsDB = (trails) => trails.map(t => AddTrailDB(t))
export const GetTrailsDB = () => Array.from(realm.objects('Trail')).map(t => deepCopy(t)).map(t => AddEdges(t));
export const GetTrailDB = (id) => AddEdges(deepCopy(realm.objectForPrimaryKey('Trail', id)));