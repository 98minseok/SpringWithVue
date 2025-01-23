<template>
  <div class ="container">
    <v-file-input v-model ="fileData" multiple label="파일 선택"></v-file-input>
    <v-btn @click = "onClickSubmitFile" text="전송"></v-btn>
  </div>

</template>


<script>
import { sendFile } from '@/api';
import { ref } from 'vue';

  export default{
    setup(){
      const fileData = ref({});
      const onClickSubmitFile = () => {
        console.log(fileData.value)
        sendFile(fileData.value);
      }

      return {fileData,onClickSubmitFile}
    }
  }
</script>


<style lang="css" scoped>
  .container{
    display :flex;
    width : 80%;
    flex-direction: column;
  }
</style>
