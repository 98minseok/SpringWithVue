<script>
import NavBar from '@components/NavBar.vue'

import { getMembers } from './api';
import { onMounted } from 'vue';
export default{
    components : {
        NavBar,
    },
    setup(){
        onMounted(async() => {
            console.log(await getMembers())
        })
    }
}
</script>

<template>
    <nav-bar></nav-bar>
    <router-view></router-view>
</template>


<style>
</style>