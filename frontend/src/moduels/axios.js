import axios from "axios"
import useCookies from "./cookies"

const {getCookie,setCookie} = useCookies();
const apiClient = axios.create({
    baseURL : 'http://localhost:8080',
    withCredentials : true,
})

const refreshAccessToken = async() => {
    try{
        const response = await apiClient.post('/api/refreshtoken',{
            refresh_token : getCookie('refreshToken')  
        },{withCredentials:true})
        return response.data
    }catch (error) {
        console.log(error);
        console.error('Refresh Token 만료 또는 유효하지 않습니다.');
        throw error;
    }   
}

apiClient.interceptors.response.use(
    (response => response), // onFulFilled

    async(error) => {
        console.log("401 에러 이후 여기로 오게됨")
        const originalRequest = error.config;

        if(error.response && error.response.status === 401 && !originalRequest._retry){
            originalRequest._retry = true;
            try{
                // Refresh Token을 이용하여 새로운 Access Token 발급
                const responseTokens = await refreshAccessToken();  
                console.log(responseTokens);
                if(responseTokens.status == "SUCCESS"){   
                    const newAccessToken = responseTokens.data.token;
                    const newRefreshToken = responseTokens.data.refreshToken;
                    setCookie('token',newAccessToken)
                    setCookie('refreshToken',newRefreshToken)
                    apiClient.defaults.headers.common['Authorization'] = `Bearer ${newAccessToken}`;
                    originalRequest.header['Authorization'] = `Bearer ${newAccessToken}`
                }

                return apiClient(originalRequest);
            }catch (refreshError){
                console.error('로그아웃 처리 필요');

                return Promise.reject(refreshError)
            }
        }

        return Promise.reject(error)
    }
)


export default apiClient;