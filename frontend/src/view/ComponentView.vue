<template>
    <div class ="main">
        <section class ="left-nav">
            <ul>
                <li v-for="component in components" class ="component-link" :class ="view_component == component ? 'active' : ''">
                    <a href="#" @click ="onClickComponent(component)">{{ component }}</a>
                </li>
            </ul>
        </section>
        <section class ="component-container">
            <section class ="component-view-container">
                {{ view_component == "" ? "컴포넌트를 선택해주세요" : ""}}
                <component :is='view_component' v-bind="component_props[view_component]" />
            </section>
            <section class="component-props-container">
                {{ view_component == "" ? "컴포넌트를 선택해주세요" : ""}}

                <ComponentOption :option_object="component_options[view_component]" :option_value ="component_props[view_component]"></ComponentOption>
            </section>
        </section>
    </div>
</template>

<script>
import { reactive, ref } from 'vue';
import FormInput from "@components/FormInput.vue"
import ComponentOption from '@/components/ComponentOption.vue';
import CustomForm from '@/components/CustomForm.vue';
import CustomButton from '@/components/CustomButton.vue'
import BoardList from '@/components/BoardList.vue';
    export default{
        setup(){
            const components = ["FormInput","Form","CustomButton","BoardList"]
            const component_props = reactive({
                FormInput : {
                    label : "Default Label",
                    placeholder : "Default PlaceHolder",
                    type : "text"
                },
                Form : {
                    inputs :  [
                        {
                            label : "ID",
                            placeholder : "아이디를 입력하세요",
                            name : "id",
                            type : "text"
                        },
                        {
                            label : "Password",
                            placeholder : "비밀번호를 입력하세요",
                            name: "password",
                            type : "password",
                        },
                        {
                            label : "email",
                            placeholder : '이메일을 입력하세요',
                            name : "email",
                            type : "email"
                        },
                        {
                            label : "ProfileImage",
                            name : "profileImage",
                            type : "file",
                        }
                    ],
                    title : "Default Title"
                }
            })

            const component_options = reactive({
                FormInput : {
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
                },
                Form : {
                    inputs : {
                        type : "array"
                    },
                    title : {
                        type : "text"
                    }
                }
            })
            
            const view_component = ref("")
            const onClickComponent = (component) => {
                view_component.value = component;
            }

            return{
                components,view_component,onClickComponent,component_props,component_options
            }
        },
        components : {
            FormInput,
            ComponentOption,
            CustomForm,
            CustomButton,
            BoardList
        }
    }
</script>

<style lang="css" scoped>
    .main{
        display:flex;
        position : absolute;
        left:0;
        top:0px;
        padding-top : 50px;
        box-sizing: border-box;
        height : 100vh;
        width  : 100vw;
    }
    .left-nav{
        flex : 1;
        padding : 10px;
        box-sizing: border-box;
        border-right : 1px solid black;
    }
    .component-container{
        width : 100%;
        flex: 9;
        display:flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        box-sizing: border-box;
    }
    .component-link{
        border-radius : 10px;
        padding-left : 5px;
    }
    .active{
        font-weight : bold;
        background-color: aquamarine;
    }

    .component-props-container{
        width: 100%;
        display:flex;
        justify-content: center;
        align-items: center;
        background-color: rgb(247, 248, 248);
        flex : 3
    }

    .component-view-container{
        flex : 7;
        display:flex;
        justify-content: center;
        align-items: center;
        border-bottom : 1px solid black;
        width:100%;
        padding : 10px;
        box-sizing: border-box;
    }
</style>