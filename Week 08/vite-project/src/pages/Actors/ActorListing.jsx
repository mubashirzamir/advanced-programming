import Actor from './Actor.jsx'
import PropTypes from 'prop-types'

const fallbackActors = [
    {name: 'Keanu Reeves'},
    {name: 'Laurence Fishburne'},
    {name: 'Carrie-Anne Moss'}
]

const ActorListing = ({actors}) => {
    const currentActors = actors || fallbackActors

    return <div>
        <h1>Actor Listing</h1>
        {currentActors.map((actor, index) => {
            return <Actor key={index} {...actor}/>
        })}
    </div>
}

ActorListing.propTypes = {
    actors: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        title: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        rating: PropTypes.number.isRequired
    }))
}

export default ActorListing