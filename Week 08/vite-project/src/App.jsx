import './App.css'
import FilmListing from './pages/Films/FilmListing.jsx'
import Home from './pages/Home/Home.jsx'
import {Route, Routes} from 'react-router-dom'
import MultiplierListing from './pages/Multiples/MultiplierListing.jsx'
import NotFound from './pages/NotFound/NotFound.jsx'
import ActorListing from './pages/Actors/ActorListing.jsx'
import Menu from './components/Menu/Menu.jsx'
import FirstExample from './pages/FirstExample/FirstExample.jsx'
import SecondExample from './pages/SecondExample/SecondExample.jsx'
import Footer from './components/Footer/Footer.jsx'

const App = () => {
    return <>
        <div className="app">
            <Menu/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/films" element={<FilmListing/>}/>
                <Route path="/actors" element={<ActorListing/>}/>
                <Route path="/multipliers" element={<MultiplierListing/>}/>
                <Route path="/example" element={<FirstExample/>}/>
                <Route path="/second-example" element={<SecondExample/>}/>
                <Route path="/*" element={<NotFound/>}/>
            </Routes>
            <Footer/>
        </div>
    </>
}
export default App
