import {Route, Routes as RouterRoutes} from 'react-router-dom'
import Home from './pages/Home/Home.jsx'
import FilmListing from './pages/Films/FilmListing.jsx'
import ActorListing from './pages/Actors/ActorListing.jsx'
import MultiplierListing from './pages/Multiples/MultiplierListing.jsx'
import FirstExample from './pages/FirstExample/FirstExample.jsx'
import SecondExample from './pages/SecondExample/SecondExample.jsx'
import NotFound from './pages/NotFound/NotFound.jsx'

const AppRoutes = () => {
    return <RouterRoutes>
        <Route path="/" element={<Home/>}/>
        <Route path="/films" element={<FilmListing/>}/>
        <Route path="/actors" element={<ActorListing/>}/>
        <Route path="/multipliers" element={<MultiplierListing/>}/>
        <Route path="/example" element={<FirstExample/>}/>
        <Route path="/second-example" element={<SecondExample/>}/>
        <Route path="/*" element={<NotFound/>}/>
    </RouterRoutes>
}

export default AppRoutes