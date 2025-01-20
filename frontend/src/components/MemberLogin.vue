<template>
    <CustomForm :inputs="formData" :title="'로그인'" :onClickSubmit = "onClickSubmit">
    </CustomForm>

    <button @click = "clickGetCookie">Get Cookie</button>
    <button @click = "clickGetTokenTest">Get TokenTest</button>
</template>

<script>
import CustomForm from './CustomForm.vue';
import { loginMember, testToken } from '@/api';
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
        const {setCookie,getCookie} = useCookie();
        const clickGetCookie = () => {
            getCookie("token");
        }
        
        const onClickSubmit = async(evt) => {
            evt.preventDefault();
            const data = {};
            formData.forEach((e) => {
                data[e.name] = e.value
            })

            const response = await loginMember(data);
            console.log(response);
            setCookie("token",response.data)
            alert(response.msg);
        }

        const clickGetTokenTest = async() => {
            const response = await testToken()
            console.log(response.msg);
        }
        return {
            formData,onClickSubmit,clickGetCookie,clickGetTokenTest
        }
    },
    components :{
        CustomForm
    }
}
</script>