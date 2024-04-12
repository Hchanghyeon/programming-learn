import { RouterProvider } from 'react-router-dom'
import { router } from './router/router'
import GlobalStyle from './styles/globalStyle'

const App = () => {

  return (
    <>
      <RouterProvider router={router} />
      <GlobalStyle />
    </>

  )
}

export default App
