import Film from './Film.jsx'
import PropTypes from 'prop-types'

const FilmListing = ({films}) => {
    return <div>
        <h1>Film Listing</h1>
        {films.map(film => {
            return <Film key={film.id} {...film}/>
        })}
    </div>
}

FilmListing.propTypes = {
    films: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        title: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        rating: PropTypes.number.isRequired
    })).isRequired
}

export default FilmListing