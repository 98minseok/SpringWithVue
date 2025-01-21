<template>
    <CustomForm :inputs="formData" :title="'로그인'" :onClickSubmit = "onClickSubmit">
    </CustomForm>
    <button @click="onClickTest"></button>
</template>

<script>
import CustomForm from './CustomForm.vue';
import { loginMember ,testToken} from '@/api';
import useCookie from '@moduels/cookies.js'

export default{
    setup(){
        const formData = [
            {
                label : "아이디",
                name : "login_id",  
                value : ""
            },  
            {
                label : "비밀번호",
                name : "password",
                type : "password",
                value : "",
            },
        ]
        const {setCookie} = useCookie();
        
        const onClickSubmit = async(evt) => {
            evt.preventDefault();
            const data = {};
            formData.forEach((e) => {
                data[e.name] = e.value
            })

            const response = await loginMember(data);
            console.log(response);
            setCookie("token",response.data.token)
            setCookie("refreshToken",response.data.refreshToken)
            alert(response.msg);
        }

        const onClickTest = async() => {
            await testToken()
        }
        return {
            formData,onClickSubmit,onClickTest
        }
    },
    components :{
        CustomForm
    }
}
</script>