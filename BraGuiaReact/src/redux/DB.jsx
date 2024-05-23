import Realm from "realm";


// Define the schema for the edges
const EdgeSchema = {
  name: 'Edge',
  primaryKey: 'id',
  properties: {
    id: 'int',
    edge_start: 'int',
    edge_end: 'int',
    edge_transport: 'string',
    edge_duration: 'int',
    edge_desc: 'string',
    edge_trail: 'int',
  },
};

const RelatedTrailSchema = {
  name: 'RelatedTrail',
  primaryKey: 'id',
  properties: {
    id: 'int',
    value: 'string',
    attrib: 'string',
    trail: 'int',
  },
};

// Define the schema for the trail
const TrailSchema = {
  name: 'Trail',
  primaryKey: 'id',
  properties: {
    id: 'int',
    trail_img: 'string',
    rel_trail: { type: 'list', objectType: 'RelatedTrail' },
    edges: { type: 'list', objectType: 'Edge' },
    trail_name: 'string',
    trail_desc: 'string',
    trail_duration: 'int',
    trail_difficulty: 'string',
  },
};

// Define the schema for the media files
const MediaSchema = {
  name: 'Media',
  primaryKey: 'id',
  properties: {
    id: 'int',
    media_file: 'string',
    media_type: 'string',
    media_pin: 'int',
  },
};

// Define the schema for the pins
const PinSchema = {
  name: 'Pin',
  primaryKey: 'id',
  properties: {
    id: 'int',
    rel_pin: { type: 'list', objectType: 'RelatedPin' },
    media: { type: 'list', objectType: 'Media' },
    pin_name: 'string',
    pin_desc: 'string',
    pin_lat: 'float',
    pin_lng: 'float',
    pin_alt: 'float',
  },
};

const RelatedPinSchema = {
  name: 'RelatedPin',
  primaryKey: 'id',
  properties: {
    id: 'int',
    value: 'string',
    attrib: 'string',
    pin: 'int',
  },
};

const realm = new Realm({ schema: [MediaSchema, PinSchema, RelatedPinSchema, TrailSchema, RelatedTrailSchema, EdgeSchema] });

export default realm;
