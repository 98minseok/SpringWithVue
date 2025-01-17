<template>
    <form class="container">
        <p class="form-title">{{ title }}</p>
        <FormInput v-for="(input,index) in inputs" :label="input.label" :placeholder="input.placeholder" :name = "input.name" :type="input.type" v-model="inputs[index].value">
        </FormInput>
        <CustomButton :onClickFunction="(evt) => {onClickSubmit(evt)}">제출하기</CustomButton>
    </form>
</template>

<script>
import FormInput from '@components/FormInput.vue';
import { useComponentProps } from '@/compositions/ComponentProps';
import { onMounted, onUpdated, ref } from 'vue';
import CustomButton from './CustomButton.vue';
export default {
    components: {
        FormInput, CustomButton
    }
    ,
    props: {
        inputs: {
            type: Object,
            default: null
        },
        title: {
            type: String,
            default: "제목",
        },
        onClickSubmit : {
            type : Function,
            default : (evt) => { evt.preventDefault() ; alert("버튼 눌림")}
        }
    },
    setup(props) {
        const { setOptionValue, setOptionObject } = useComponentProps();
        const default_value = [
            {
                label: "ID",
                placeholder: "아이디를 입력하세요",
                name: "id",
                type: "text"
            },
            {
                label: "Password",
                placeholder: "비밀번호를 입력하세요",
                name: "password",
                type: "password",
            },
            {
                label: "email",
                placeholder: '이메일을 입력하세요',
                name: "email",
                type: "email"
            },
            {
                label: "ProfileImage",
                name: "profileImage",
                type: "file",
            }
        ]

        const props_object = {
            label: {
                type: "text"
            },
            placeholder: {
                type: "text"
            },
            type: {
                type: "select",
                options: ["text", "password", "email", "file"]
            }
        }
        
    }
}
</script>

<style lang="css" scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.form-title {
    font-size: 24px;
}
</style>
