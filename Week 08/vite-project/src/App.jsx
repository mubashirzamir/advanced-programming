import './App.css'
import Menu from './components/Menu/Menu.jsx'
import Footer from './components/Footer/Footer.jsx'
import AppRoutes from './AppRoutes.jsx'

const App = () => {
    return <>
        <div className="app">
            <Menu/>
            <AppRoutes/>
            <Footer/>
        </div>
    </>
}

export default App
