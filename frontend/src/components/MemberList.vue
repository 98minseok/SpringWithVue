<template>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>이름</th>
                <th>닉네임</th>
                <th>비밀번호</th>
                <th>이메일</th>
                <th>아이디</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for ="(member,index) in member_data" :key ="member.id">
                <td align="center">{{ index+1 }}</td>
                <td v-for ="key in Object.keys(member)" v-show="key != 'id'":key = "key + '-' + index">
                    {{ member[key] }}
                </td>
                <td>
                    <div class ="button-container">
                        <CustomButton :onClickFunction = "() => {onClickUpdate(member.id); console.log(member.id)}">수정</CustomButton>
                        <CustomButton :onClickFunction = "() => {onClickDelte(member.id); console.log(member.id)}">삭제</CustomButton>
                    </div>
                    
                </td>
            </tr>
        </tbody>
    </table>
</template>
<script>
import { getMembers,deleteMember } from '@/api';
import { inject, onBeforeMount, ref } from 'vue';
import CustomButton from './CustomButton.vue';

export default{
    setup(){
        const update_id = inject("update_id")
        const view_api = inject("view_api")
        const member_data = ref({})
        const onClickDelte = async(id) => {
            const response = await deleteMember(id);
            alert(response.msg)

            member_data.value = member_data.value.filter((e) => e.id != id)
        }
        
        const onClickUpdate = (id) => {
            update_id.value = id
            view_api.value = 'MemberUpdate'
        }

        onBeforeMount(async() => {
            const response = await getMembers();
            if(response.status === "SUCCESS"){
                member_data.value = response.data;
            }
        })


        return {member_data , onClickDelte , onClickUpdate}
    }
    ,
    components : {
        CustomButton
    }
}
</script>

<style scoped>
    table,tr,td{
        border-collapse: collapse;
        border : 1px solid black;
    }
    td{
        padding : 10px 20px;
    }
    .button-container{
        display:flex;
        gap : 10px;
    }
</style>