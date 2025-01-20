<template>
    <form class="container">
        <p class="form-title">{{ title }}</p>
        <FormInput :key = "'input-'+index" v-for="(input,index) in inputs" :label="input.label" :placeholder="input.placeholder" :name = "input.name" :type="input.type" v-model="input.value">
        </FormInput>
        <CustomButton :onClickFunction="(evt) => {onClickSubmit(evt)}">제출하기</CustomButton>
    </form>
</template>

<script>
import FormInput from '@components/FormInput.vue';
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
    setup() {
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
