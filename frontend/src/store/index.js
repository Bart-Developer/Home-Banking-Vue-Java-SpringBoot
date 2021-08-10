import { createStore } from 'vuex'


export default createStore({
  state: {
    login: "",
    data:{}
  },
  mutations: {
   
    setLogin(state, payload){
      state.login=payload;
    },
    setData(state, payload){
      state.data=payload;
    }

  },
  actions: {
    setLogin({commit}, payload){
      commit('setLogin', payload);
    },
    setData({commit}, payload){
      commit('setData', payload);
    }
  },
  modules: {
    getters:{
      getLogin(state) {
        return state.login;
      },
      getData(state) {
        return state.data;
      },
    }
  }
});
