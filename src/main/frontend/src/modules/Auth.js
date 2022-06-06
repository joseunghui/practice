import {createAction , handleActions} from 'redux-actions';

// sample 액션 타입 정의
// module/ACTION_TYPE. 액션 타입은 대문자로, 앞에 모듈을 붙여주어야 액션 이름 중첩을 방지할 수 있음.
const SAMPLE_ACTION = 'auth/SAMPLE_ACTION';

// 액션 생성자. createAction 함수는 매번 객체를 직접 만들어 줄 필요 없이 더욱 간단하게 액션 생성 함수를 선언할 수 있음. 사용으로 액션 추가 데이터는 payload 사용.
// 액션 생성자는 export
export const sampleAction = createAction(SAMPLE_ACTION);

// 초기 상태 정의
const initialState = {};

// 리듀서 함수
// 리듀서 함수도 더 간단하고 가독성 높게 사용하기 위해 handleActions 함수 사용
// 리듀서 함수는 export default 로
const auth = handleActions(
    {
        [SAMPLE_ACTION] :  (state,action) => state,
    },
    initialState,
);


export default auth;