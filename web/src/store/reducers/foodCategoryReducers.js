import {
    CATEGORIES_LIST_REQUEST, CATEGORIES_LIST_SUCCESS, CATEGORIES_LIST_FAIl
} from "../../constants";

function categoryListReducer(state = { categories: [] }, action) {
    switch (action.type) {
        case CATEGORIES_LIST_REQUEST:
            return { loading: true, categories: [] };
        case CATEGORIES_LIST_SUCCESS:
            return { loading: false, categories: action.payload };
        case CATEGORIES_LIST_FAIl:
            return { loading: false, error: action.payload };
        default:
            return state;
    }
}

export {
    categoryListReducer
};
