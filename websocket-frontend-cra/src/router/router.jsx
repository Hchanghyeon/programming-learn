import { createBrowserRouter } from 'react-router-dom';
import { LoginPage } from '../pages/LoginPage/LoginPage';
import { IndexPage } from '../pages/IndexPage/IndexPage';
import RegisterPage from '../pages/RegisterPage/RegisterPage';
import { ChatRoomList } from '../pages/ChatRoomListPage/ChatRoomListPage';
import ChatRoomCreatePage from '../pages/ChatRoomCreatePage/ChatRoomCreatePage';
import ChatRoomPage from '../pages/ChatRoomPage/ChatRoomPage';

export const router = createBrowserRouter([
    {
      path: "/",
      element: <IndexPage/>,
    },
    {
      path: "login",
      element: <LoginPage/>,
    },
    {
      path: "register",
      element: <RegisterPage/>,
    },
    {
      path: "chatrooms",
      element: <ChatRoomList/>,
    },
    {
      path: "chatrooms/create",
      element: <ChatRoomCreatePage/>,
    },
    {
      path: "chatrooms/page",
      element: <ChatRoomPage/>,
    },
  ]);