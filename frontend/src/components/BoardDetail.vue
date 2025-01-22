<template>
  <v-card
    class="mx-auto"
    prepend-icon="$vuetify"
    :subtitle= "board_data.author + '     작성일 ' + board_data.date + '      조회수 ' +board_data.view + '      좋아요 ' + board_data.likeCount|| null"
    width="80%"
  >
    <template v-slot:title>
      <span class="font-weight-black">{{ board_data.title }}</span>
      <button style ="margin-left : 30px ; padding : 5px" @click = "onClickHeart">♥</button>
    </template>
    <v-card-text class="bg-surface-light pt-4">
      {{ board_data.content }}
    </v-card-text>

    <template v-slot:actions>
      <v-btn @click= "onClickEdit" text="수정"></v-btn>
      <v-btn @click= "onClickDelete" text="삭제"></v-btn>
    </template>
  </v-card>
</template>

<script>
import { deleteBoard, getBoardById, hitHeart } from '@/api';
import { inject, onBeforeMount, ref } from 'vue';

  export default{
    setup(){
      const board_id = inject('board_id');
      const view_api = inject('view_api');
      const board_data = ref({})
      const onClickHeart = async() => {
        const response =  await hitHeart(board_id.value);
        board_data.value.likeCount ++;
        alert(response.data.msg);
      }
      onBeforeMount(async() => {
        if(board_id.value != -1){
          const response = await getBoardById(board_id.value)
          board_data.value = response.data.data;
          console.log(response);
        }

      })

      const onClickEdit = () => {
        view_api.value = "BoardForm"
      }

      const onClickDelete = async() => {
        alert( (await deleteBoard(board_id.value)).data.msg);
        view_api.value = "BoardList"
      }
      return { board_id, board_data , onClickHeart , onClickEdit , onClickDelete }
    }

  }
</script>

<style lang="css" scoped>

</style>
