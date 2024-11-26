import {Link} from 'react-router-dom'

const Menu = () => {
    return <div>
        <h1>Menu</h1>
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/actors">Actors</Link></li>
                <li><Link to="/films">Films</Link></li>
                <li><Link to="/multipliers">Multipliers</Link></li>
                <li><Link to="/example">First Example</Link></li>
                <li><Link to="/second-example">Second Example</Link></li>
            </ul>
        </nav>
    </div>
}

export default Menu
