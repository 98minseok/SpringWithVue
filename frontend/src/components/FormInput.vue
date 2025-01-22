<template>
    <div class ="forminput-container">
        <label>{{ label }}</label>
        <div class ="separator"></div>
        <input class ="custom-input" :type="type" :name ="name" :placeholder="placeholder" :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)">
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
            },
            name : String,
            modelValue : String,
        },
        setup() {
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
    .custom-input{
      border-bottom : 1px solid black;
    }
</style>
