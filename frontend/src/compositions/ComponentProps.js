import { reactive } from "vue";

export const useComponentProps = () => {
    const state = reactive({
        option_value: {}, // 빈 객체로 초기화
        option_object: {}, // 빈 객체로 초기화
    });

    const SET_OPTION_VALUE = (data) => {
        state.option_value = data;
    };

    const SET_OPTION_OBJECT = (data) => {
        state.option_object = data;
    };

    const GET_OPTION_VALUE = () => state.option_value;
    const GET_OPTION_OBJECT = () => state.option_object;

    return {
        setOptionValue: SET_OPTION_VALUE,
        setOptionObject: SET_OPTION_OBJECT,
        getOptionValue: GET_OPTION_VALUE,
        getOptionObject: GET_OPTION_OBJECT,
    };
};
