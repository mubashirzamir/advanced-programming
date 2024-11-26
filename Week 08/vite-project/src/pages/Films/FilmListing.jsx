import Film from './Film.jsx'
import PropTypes from 'prop-types'

const fallbackFilms = [
    {
        id: 1,
        title: 'The Shawshank Redemption',
        description: 'Andy Dufresne, a successful banker, is arrested for the murders of his wife and her lover, and is sentenced to life imprisonment at the Shawshank prison. He becomes the most unconventional prisoner.',
        rating: 'R',
        category: 'Thriller'
    },
    {
        id: 2,
        title: 'The Godfather',
        description: 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
        rating: 'R',
        category: 'Crime'
    },
    {
        id: 3,
        title: 'The Dark Knight',
        description: 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
        rating: 'PG-13',
        category: 'Action'
    }
]

const FilmListing = ({films}) => {
    const currentFilms = films || fallbackFilms

    return <div>
        <h1>Film Listing</h1>
        {currentFilms.map(film => {
            return <Film key={film.id} {...film}/>
        })}
    </div>
}

FilmListing.propTypes = {
    films: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        title: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        rating: PropTypes.string.isRequired
    }))
}

export default FilmListing