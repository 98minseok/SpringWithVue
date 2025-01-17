import axios from "axios"

const useAxios = () => {

    const BASED_URL = "http://localhost:8080"
    const createURL = (url) => BASED_URL + url;
    const axiosGet = async(url,onSuccess,onFailed) => await axios.get(createURL(url)).then((data) => {
        if(onSuccess){
            onSuccess(data);
        }
        else{
            console.log(data);
        }
    }).catch((err) => {
        if(onFailed){
            onFailed(err)
        }
        else{
            console.log(err);
        }
    })

    const axiosPost = async(url,data,onSuccess,onFailed) => {
        console.log(data,"axios")
        return await axios.post(createURL(url),data,{
        headers: {
            'Content-Type': 'application/json' // 명시적으로 설정
        },
    }).then((data) => {
        if(onSuccess){
            onSuccess(data);    
        }
        else{
            console.log(data);
        }
    }).catch((err) => {
        if(onFailed){
            onFailed(err)
        }
        else{
            console.log(err);
        }
    })
    }
    return { axiosGet , axiosPost}
    
}

export default useAxios;