import {legacy_createStore as createStore} from 'redux'

const initialState = {
    sidebarShow: true,
    theme: 'light'
}

const cchangeState = (state = initialState, {type, ...reset}) => {
    switch (type) {
        case 'set':
            return {...state,...reset }
        default: 
            return state
    }
}
export const store = createStore(cchangeState)
export default store