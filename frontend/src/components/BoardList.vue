<template>
    <v-data-table
      :headers="headers"
      :items="boards"
      density="compact"
      item-key="name"
    ></v-data-table>
  </template>

<script>
import { getBoards } from '@/api';
import { onMounted, ref } from 'vue';

  export default {
    setup(){
        const headers =  [
        { title: 'No', align: 'center', sortable: true, key: 'no' },
        { title: 'Title', align: 'start', key: 'title' },
        { title: 'Author', align: 'center', key: 'author' },
        { title: 'Date', align: 'center', key: 'date' },
        { title: 'View', align: 'center', key: 'view' },
        { title :'Like' , align : 'center', key : 'likeCount'},
      ]
       const boards = ref([]);
       onMounted(async() => {
        const response = await getBoards()
        boards.value = response.data.data
        console.log(response.data);
       })
      return { headers , boards }
    },

  }
</script>

<style>

</style>
