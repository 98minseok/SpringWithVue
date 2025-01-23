<template>
  <div class ="container">
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

  <v-textarea v-model ="reply_value" width ="80%">

  </v-textarea>
  <v-btn @click = "onSubmitReply" :text="isEdit ? '댓글 수정' : '댓글 작성'"></v-btn>
  <v-btn v-if ="isEdit" @click = "onClickCancelEdit" text = "댓글 수정 취소"></v-btn>
  <v-list width = "80%" lines="one">
  <v-list-item
    v-for="reply in reply_data"
    :key="reply"
    :title="reply.userName"
    :subtitle="reply.content"
  >
    <template v-slot>
      <v-btn @click = "() => onClickEditReply(reply)" text="수정"></v-btn>
      <v-btn @click = "() => onClickDelteReply(reply.id)" text="삭제"></v-btn>
    </template>
</v-list-item>
  </v-list>
  </div>



</template>

<script>
import { deleteBoard, deleteReply, editReply, getBoardById, getReply, hitHeart, saveReply } from '@/api';
import { inject, onBeforeMount, ref } from 'vue';

  export default{
    setup(){
      const board_id = inject('board_id');
      const view_api = inject('view_api');
      const login_user = inject('loginUser')
      const board_data = ref({})
      const reply_data = ref([]);
      const reply_value = ref("");
      const isEdit = ref(false);
      const editReplyId = ref(-1);

      const onSubmitReply = async() => {
        const data = {};
        data.userId = login_user.value
        data.content = reply_value.value
        data.parentId = board_id.value

        if(isEdit.value){
          data.id = editReplyId.value
          const response = await editReply(data)
          alert(response.msg);
        }
        else{
          const response = await saveReply(data)
          alert(response.msg);
        }

        await loadReply()
        onClickCancelEdit();
      }

      const loadReply = async () => {
          const reply_response = await getReply(board_id.value)
          reply_data.value = reply_response.data;
          console.log(reply_response);
      }
      const onClickHeart = async() => {
        const response =  await hitHeart(board_id.value);
        board_data.value.likeCount ++;
        alert(response.data.msg);
      }

      onBeforeMount(async() => {
        if(board_id.value != -1){
          const board_response = await getBoardById(board_id.value)
          board_data.value = board_response.data.data;
          console.log(board_response);

          await loadReply()

        }

      })

      const onClickEditReply = (reply) => {
        isEdit.value = true;
        editReplyId.value = reply.id
        reply_value.value = reply.content
      }

      const onClickCancelEdit = () => {
        isEdit.value = false;
        editReplyId.value = -1
        reply_value.value = ''
      }
      const onClickDelteReply = async(id) => {
        alert( (await deleteReply(id)).msg );
        await loadReply()
      }
      const onClickEdit = () => {
        view_api.value = "BoardForm"
      }

      const onClickDelete = async() => {
        alert( (await deleteBoard(board_id.value)).data.msg);
        view_api.value = "BoardList"
      }
      return { board_id, board_data , onClickHeart , onClickEdit , onClickDelete , reply_data ,reply_value , onSubmitReply , onClickDelteReply, onClickEditReply , isEdit , editReplyId , onClickCancelEdit}
    }

  }
</script>

<style lang="css" scoped>
  .container{
    display:flex;
    width:100%;
    flex-direction: column;
    gap: 20px;
    align-items: center;
  }
</style>
