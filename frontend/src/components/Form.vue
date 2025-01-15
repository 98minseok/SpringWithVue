<template>
    <div class ="container">
        <p class ="form-title">{{ title }}</p>
        <FormInput v-for="input in inputs" :label="input.label" :placeholder="input.placeholder" :type="input.type"></FormInput>
    </div>
</template>

<script>
import FormInput from '@components/FormInput.vue';
import { useComponentProps } from '@/compositions/ComponentProps';
import { onMounted, ref } from 'vue';
export default{
    components : {
        FormInput
    }
    ,
    props : {
        inputs : {
            type : Object,
            default : null
        },
        title : {
            type : String,
            default : "제목",
        }
    },
    setup(){
        const {setOptionValue,setOptionObject} = useComponentProps();
        const default_value =  [
            {
                label : "ID",
                placeholder : "아이디를 입력하세요",
                type : "text"
            },
            {
                label : "Password",
                placeholder : "비밀번호를 입력하세요",
                type : "password",
            },
            {
                label : "email",
                placeholder : '이메일을 입력하세요',
                type : "email"
            },
            {
                label : "ProfileImage",
                type : "file",
            }
        ]
        
        const props_object = {
            label : {
                type : "text"
            },
            placeholder : {
                type : "text"
            },
            type : {
                type : "select",
                options : ["text","password","email","file"]
            }
        }

        const inputs = ref(default_value || props.inputs);

        return {inputs}
    }
}
</script>

<style lang="css" scoped>
    .container{
        display:flex;
        flex-direction: column;
        gap : 20px;
    }
    .form-title{
        font-size : 24px;
    }
</style>