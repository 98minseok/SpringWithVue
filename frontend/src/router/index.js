import { createMemoryHistory, createRouter } from "vue-router";

import APIView from "@view/APIView.vue";
import ComponentView from "@view/ComponentView.vue";
import Home from "@view/Home.vue"
import RegisterView from "@view/RegisterView.vue";
const routes = [
    { path : '/components', component : ComponentView },
    { path : '/api', component : APIView},
    { path : '/', component : Home},
    { path : '/register' , component : RegisterView}
]

const router = createRouter({
    history : createMemoryHistory(),
    routes,
})

export default router;