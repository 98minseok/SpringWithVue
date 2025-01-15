<template>
    <div class ="forminput-container">
        <label>{{ label }}</label>
        <div class ="separator"></div>
        <input :type="type" :placeholder="placeholder">
    </div>
</template>

<script>
    export default{
        props:{
            title : String,
            label : String,
            placeholder : String,
            type : {
                type : String,
                default : "text"
            }
        }
    }
</script>

<style lang="css" scoped>
    .forminput-container{
        display:flex;
        width : 300px;
    }
    .forminput-container label{
        flex : 2;
    }
    .forminput-container input{
        flex : 2;

    }
    .separator{
         width : 10px;
    }
</style>