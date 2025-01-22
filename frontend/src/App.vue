<script>
import NavBar from '@components/NavBar.vue'

import { getMembers } from './api';
import { onMounted, provide, ref } from 'vue';
export default{
    components : {
        NavBar,
    },
    setup(){
        onMounted(async() => {
            console.log(await getMembers())
        })

        const loginUser = ref("")
        provide('loginUser',loginUser);

    }
}
</script>

<template>
    <nav-bar></nav-bar>
    <router-view></router-view>
</template>


<style>
</style>
