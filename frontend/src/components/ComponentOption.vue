<template>
    <div class="container">
    <table class ="option-list" v-if="option_object">
        <thead>
        <tr class ="option-item option-header">
            <th>Option</th>
            <th class ="separator"></th>
            <th>Value</th>
        </tr>
        </thead>
    <tbody>
        
        <tr class= "option-item" v-for="options in Object.keys(option_object)">
            <td>{{ options }}</td>
            <td class ="separator"></td>
            <td>
                <input v-model="option_value[options]" type ="text" v-if ='option_object[options].type == "text"'>
                <select v-model="option_value[options]" v-else-if="option_object[options].type == 'select'">
                    <option v-for="option in option_object[options].options" :value = "option">{{ option }}</option>
                </select>
                <ul class ="array-container" v-else-if="option_object[options].type == 'array'">
                    <li class ="array-items" v-for ="value in option_value[options]">
                        {{ value }}
                        <div>
                            <button>Edit</button>
                            <button>Delete</button>
                        </div>
                        
                    </li>
                    <li class ="array-items-add">
                        <button>+</button>
                    </li>
                </ul>
            </td>
        </tr>
    </tbody>
    </table>
</div>
</template>

<script>
    export default{
        props:{
            option_object : {
                type : Object,
                default : null
            },
            option_value : {
                type : Object,
                default : null,
            }
        },
        setup(props){
            console.log(props)
        }
    }
</script>

<style lang="css" scoped>
    *{
        font-size : 16px;
    }
    .option-list{
        width : 100%;
        padding : 30px;
        list-style-type: none;
        flex-direction: column;
        gap : 10px;
        border : 1px solid black;
        border-radius : 10px;
    }
    .option-item{
        border : 1px solid black;
    }
    .option-header *{
        font-size : 20px;
        font-weight: bold;
    }
    .separator{
        width : 100px;
    }
    .array-items{
        position : relative;
        display:flex;
        justify-content: space-between;
        gap : 20px;
        margin-bottom : 5px;
    }
    .array-items button{
    }
    .array-items-add{
        display:flex;
        justify-content: center;
    }
    .array-container{
        padding : 0px;
        margin : 0px;
    }
</style>