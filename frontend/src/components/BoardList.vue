<template>
  <v-data-table
        :headers="headers"
        :items="boards"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td v-for ="header in headers" :key ="header.key" :align = "header.align">
              <span v-if ="header.key == 'title'" @click="() => {onClickTitle(item)}" class ="link">
                {{ item[header.key] }}
              </span>
              <span v-else>
                {{ item[header.key] }}
              </span>
            </td>
          </tr>
        </template>
      </v-data-table>
    </template>

  <script>
  import { getBoards } from '@/api';
  import { inject, onMounted, ref } from 'vue';

    export default {
      setup(){
          const headers =  [
          { title: 'No', align: 'center', sortable: true, key: 'no' },
          { title: 'Title', align: 'start', key: 'title' },
          { title: 'Author', align: 'center', key: 'author' },
          { title: 'Date', align: 'center', key: 'date' },
          { title: 'View', align: 'center', key: 'view' },
          { title :'Like' , align : 'center', key : 'likeCount'},
          { title :'',}
        ]
        const board_id = inject("board_id")
        const view_api = inject("view_api")
         const onClickTitle = (item) => {
            board_id.value = item.id;
            view_api.value = 'BoardDetail'
         }
         const boards = ref([]);
         onMounted(async() => {
          board_id.value = -1
          const response = await getBoards()
          boards.value = response.data.data
          console.log(response.data);
         })
        return { headers , boards , onClickTitle }
      },

    }
  </script>

  <style>
    .link{
      cursor: pointer;
      padding : 10px 0px;
    }
  </style>
