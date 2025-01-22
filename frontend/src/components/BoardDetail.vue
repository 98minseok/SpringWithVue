<template>
  <v-card
    class="mx-auto"
    prepend-icon="$vuetify"
    :subtitle= "board_data.author + '     작성일 ' + board_data.date + '      조회수 ' +board_data.view + '      좋아요 ' + board_data.likeCount|| null"
    width="80%"
  >
    <template v-slot:title>
      <span class="font-weight-black">{{ board_data.title }}</span>
    </template>

    <v-card-text class="bg-surface-light pt-4">
      {{ board_data.content }}
    </v-card-text>
  </v-card>
</template>

<script>
import { getBoardById } from '@/api';
import { inject, onBeforeMount, ref } from 'vue';

  export default{
    setup(){
      const board_id = inject('board_id');
      const board_data = ref({})

      onBeforeMount(async() => {
        if(board_id.value != -1){
          const response = await getBoardById(board_id.value)
          board_data.value = response.data.data;
          console.log(response);
        }

      })

      return { board_id, board_data}
    }

  }
</script>

<style lang="css" scoped>

</style>
