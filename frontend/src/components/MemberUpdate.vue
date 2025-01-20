<template>
    <CustomForm :inputs = "formData" title ="회원 정보 수정" :onClickSubmit = "onClickSubmit"></CustomForm>
</template>

<script>
import CustomForm from '@/components/CustomForm.vue';
import { getMemberById, updateMember } from '@/api';
import { useRouter } from 'vue-router';
import { inject, onMounted,  ref } from 'vue';
export default{
    setup(){
        const router = useRouter();
        const update_id = inject('update_id');
        const formData = ref([
            {
                label : "이름",
                name : "name",  
                value : "",
            },
            {
                label : "닉네임",
                name : "nickname",
                value : "",
            },
            {
                label : "이메일",
                name : "email",
                type : "email",
                value : "",
            },
        ])
        
        onMounted(async() => {
            if(update_id.value != -1){
                const response = await getMemberById(update_id.value)
                formData.value = formData.value.map(e => ({
                    ...e,
                    value: response.data[e.name] || ""
                }));
                console.log(formData.value);
                console.log(response);
            }
            
        })
        const onClickSubmit = async(evt) => {
            evt.preventDefault();
            const data = {}
            formData.value.forEach((e) => {
                data[e.name] = evt.target.parentNode[e.name].value
            })
            data.id = update_id.value;
            const response = await updateMember(data);
            
            if(response.status == "SUCCESS") {
                console.log("이동");
                alert(response.msg);
                router.push('/api')
            }
            else {
                formData.value.forEach((e) => {
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