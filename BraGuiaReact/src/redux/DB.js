import Realm from "realm";

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
const UserSchema = {
  name: 'User',
  primaryKey: 'username',
  properties: {
    username: 'string',
    user_type: 'string',
    last_login: 'string',
    is_superuser: 'bool',
    first_name: 'string',
    last_name: 'string',
    email: 'string',
    is_staff: 'bool',
    is_active: 'bool',
    date_joined: "string",
  }
}

const SocialSchema = {
  name: 'Social',
  properties: {
    social_app: 'string',
    social_name: 'string',
    social_url: 'string',
    social_share_link: 'string'
  }
};

const ContactSchema = {
  name: 'Contact',
  properties: {
    contact_app: 'string',
    contact_name: 'string',
    contact_phone: 'string',
    contact_url: 'string',
    contact_mail: 'string',
    contact_desc: 'string',
  }
};

const PartnerSchema = {
  name: 'Partner',
  properties: {
    partner_app: 'string',
    partner_name: 'string',
    partner_phone: 'string',
    partner_url: 'string',
    partner_mail: 'string',
    partner_desc: 'string',
  }
};

const AppSchema = {
  name: 'App',
  properties: {
    app_name: 'string',
    app_desc: 'string',
    app_landing_page_text: 'string',
    socials: { type: 'list', objectType: 'Social' },
    contacts: { type: 'list', objectType: 'Contact' },
    partners: { type: 'list', objectType: 'Partner' }
  },
};

const TrailHistorySchema = {
  name: 'TrailHistory',
  primaryKey: 'id',
  properties: {
    id: 'int',
    trailName: 'string'
  },
}


const PinHistorySchema = {
  name: 'PinHistory',
  primaryKey: 'id',
  properties: {
    id: 'int',
    pointOfInterestName: 'string'
  },
};

const realm = new Realm({
  schema: [
    MediaSchema,
    PinSchema,
    RelatedPinSchema,
    TrailSchema,
    RelatedTrailSchema,
    EdgeSchema,
    UserSchema,
    SocialSchema,
    ContactSchema,
    PartnerSchema,
    AppSchema,
    TrailHistorySchema,
    PinHistorySchema]
});

export default realm;
