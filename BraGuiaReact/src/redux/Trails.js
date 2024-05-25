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

export const AddTrail = (trail) => {
  trail.edges.forEach(edge => {
    AddPinDB(edge.edge_start)
    AddPinDB(edge.edge_end)
  })
  trail.edges = edgesConverter(trail.edges)
  realm.write(() => {
    realm.create('Trail', trail)
  })
}

export const AddTrails = (trails) => {
  trails.forEach(t => AddTrail(t))
}

export const GetTrailsDB = () => {
  return Array.from(realm.objects('Trail'));
}

export const GetTrailDB = (id) => {
  return new Promise((resolve, reject) => {
    try {
      const trail = realm.objectForPrimaryKey('Trail', id);
      if (trail) {
        resolve(trail);
      } else {
        reject(new Error(`Trail with id ${id} not found`));
      }
    } catch (error) {
      reject(error);
    }
  });
};