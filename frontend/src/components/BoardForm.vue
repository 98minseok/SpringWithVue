<template>
  <div class ="container">
    <v-form class ="form">
      <v-text-field
      v-model="title"
      label ="Title"></v-text-field>
      <v-textarea
        label ="Content"
        v-model="content"
        auto-grow
        rows = 10
      ></v-textarea>
      <v-btn @click = "(evt) => {onClickSubmit(evt)}" class="mt-2" type="submit">Submit</v-btn>
    </v-form>
  </div>
</template>

<script>
import { editBoard, getBoardById, saveBoard } from '@/api';
import { inject, onMounted, ref } from 'vue';

  export default{
    setup(){

      const title = ref("");
      const content = ref("");
      const loginUser = inject('loginUser')
      const board_id = inject('board_id');
      const onClickSubmit = async function(evt){
        evt.preventDefault();
        const data = {
          id : board_id.value == -1 ? '' : board_id.value,
          userId : loginUser.value,
          title : title.value,
          content : content.value
        }
        if(board_id.value == -1){
          const response = await saveBoard(data)
          alert(response.msg);
        }
        else{
          const response = await editBoard(data)
          alert(response.msg);
        }


      }
      onMounted(async() => {
        if(board_id != -1){
          const response = await getBoardById(board_id.value);
          const data = response.data.data
          title.value = data.title
          content.value = data.content


        }
      })

      return { onClickSubmit,title,content }
    }

  }
</script>

<style lang="css" scoped>
  .container{
    display:flex;
    align-items: center;
    justify-content: center;
    width : 100%;
    padding : 100px;
  }
  .form{
    display:flex;
    flex-direction: column;
    width : 80%;
  }
</style>
