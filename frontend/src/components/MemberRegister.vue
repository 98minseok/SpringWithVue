<template>
    <CustomForm :inputs = "formData" title ="회원가입" :onClickSubmit = "onClickSubmit"></CustomForm>
</template>

<script>
import CustomForm from '@/components/CustomForm.vue';
import useAxios from '@/moduels/axios';
import { getMembers,postMember } from '@/api';
import { useRouter } from 'vue-router';
export default{
    setup(){
        const {axiosPost , axiosGet} = useAxios();
        const router = useRouter();
        const formData = [
            {
                label : "이름",
                name : "name",
            },
            {
                label : "아이디",
                name : "login_id",
            },  
            {
                label : "비밀번호",
                name : "password",
                type : "password"
            },
            {
                label : "비밀번호 확인",
                name : "passwordCheck",
                type : "password",
            },
            {
                label : "닉네임",
                name : "nickname",
            },
            {
                label : "이메일",
                name : "email",
                type : "email",
            },
        ]
        
        const onClickSubmit = async(evt) => {
            evt.preventDefault();
            const data = {}
            let message = "회원가입이 완료되었습니다."
            formData.forEach((e) => {
                data[e.name] = evt.target.parentNode[e.name].value
            })
            if(data["password"] !== data["passwordCheck"]){
                message = "두 비밀번호가 서로 다릅니다."
                alert(message);
                return;
            }

            const response = await postMember(data);
            
            alert(response.msg);
            if(response.status == "SUCCESS") {
                console.log("이동");
                router.push('/components')
            }
            else {
                formData.forEach((e) => {
                evt.target.parentNode[e.name].value = ""
            })
        }

        }

        return {
            formData,
            onClickSubmit
        }
    },
    components : {
        CustomForm
    }
}
</script>