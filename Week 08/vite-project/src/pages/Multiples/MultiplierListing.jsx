import Multiplier from './Multiplier.jsx'
import PropTypes from 'prop-types'

const fallbackMultiples = Array.from({length: 12}, (_, i) => i + 1)

const MultiplierListing = ({multiples}) => {
    const currentMultiples = multiples || fallbackMultiples

    return <div>
        <h1>Multiplier Listing</h1>
        {currentMultiples.map(multiple => {
            return <Multiplier key={multiple} multiple={multiple}/>
        })}
    </div>
}

MultiplierListing.propTypes = {
    multiples: PropTypes.arrayOf(PropTypes.number)
}

export default MultiplierListing