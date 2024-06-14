const server = 'http://192.168.85.186'
export const approute = () => `${server}/app`
export const loginroute = () => `${server}/login`
export const trailsroute = () => `${server}/trails`
export const trailroute = (id) => `${server}/trail/${id}`
export const userroute = () => `${server}/user`
export const pinroute = (id) => `${server}/pin/${id}`
export const pinsroute = () => `${server}/pins`