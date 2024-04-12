import axiosInstance from "../axiosInstance";

export const createChatRoom = async (groupChatRoomCreateRequest) => {
    const { data } = await axiosInstance.post(`/chat/rooms`, groupChatRoomCreateRequest,{
        headers: {
            Authorization: "Bearer " + localStorage.getItem('accessToken')
        }
    }
);

    return data;    
}