import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate"

export default createStore({
  state: {
    userData: {},
    at: "",
    at_time: "",
    cart: [] as any
  },

  plugins: [createPersistedState()],

  mutations: {

    setAuth(state, token)
    {
      state.at = token
      state.at_time = String(new Date())
    },

    removeAuth(state)
    {
      state.at = ""
      state.at_time = ""
      state.userData = {}
    },

    setUserData(state, data)
    {
      state.userData = data
    },

    addToCart(state, item)
    {
      const i = state.cart.findIndex((i: any) => i["p_id"] === item["p_id"])

      if (i > -1)
      {
        state.cart[i]["p_num"]++
      }
      else
      {
        state.cart.push({
              p_id : item["p_id"],
              p_name: item["p_name"],
              p_image: item["p_image"],
              p_code: item["p_code"],
              p_price: item["p_price"],
              p_num: 1
            })
      }
    },

    removeFromCart(state, id)
    {
      const i = state.cart.findIndex((b: any) => b["p_id"] === id)

      if (i > -1)
      {
        if (state.cart[i]["p_num"] <= 1)
        {
          const newCart: any = []

          state.cart.forEach((item: any) =>
          {
            if (item["p_id"] !== id)
            {
              newCart.push(item)
            }
          })

          state.cart = newCart
        }
        else
        {
          state.cart[i]["p_num"]--
        }
      }
    }
  },

  getters: {
    getAuth: state =>
    {
      return `Bearer ${state.at}`
    },

    getCartSize: state =>
    {
      let size = 0

      state.cart.forEach((item: any) =>
      {
        size += item["p_num"]
      })

      return size
    },
  },

  actions: {

  },

  modules: {

  }
})
