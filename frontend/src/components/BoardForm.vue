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
import { saveBoard } from '@/api';
import { inject, ref } from 'vue';

  export default{
    setup(){

      const title = ref("");
      const content = ref("");
      const loginUser = inject('loginUser')
      const onClickSubmit = async function(evt){
        evt.preventDefault();
        const data = {
          userId : loginUser.value,
          title : title.value,
          content : content.value
        }
        const response = await saveBoard(data)
        alert(response.msg);
      }

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
