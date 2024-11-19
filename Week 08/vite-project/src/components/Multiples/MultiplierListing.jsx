import Multiplier from './Multiplier.jsx'
import PropTypes from 'prop-types'

const MultiplierListing = ({multiples}) => {
    return <div>
        <h1>Multiplier Listing</h1>
        {multiples.map(multiple => {
            return <Multiplier key={multiple} multiple={multiple}/>
        })}
    </div>
}

MultiplierListing.propTypes = {
    multiples: PropTypes.arrayOf(PropTypes.number).isRequired
}

export default MultiplierListing