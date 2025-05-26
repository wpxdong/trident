import {
    CAvatar,
    CBadge,
    CDropdown,
    CDropdownDivider,
    CDropdownHeader,
    CDropdownItem,
    CDropdownMenu,
    CDropdownToggle,
  } from '@coreui/react'

import {
    cilBell,
    cilCreditCard,
    cilCommentSquare,
    cilEnvelopeOpen,
    cilFile,
    cilLockLocked,
    cilSettings,
    cilTask,
    cilUser
} from '@coreui/icons'

import CIcon from '@coreui/icons-react'
import avatar8 from './../../assets/images/avatars/8.jpg'

const AppHeaderDropdown = () => {
    return (
       <CDropdown variant='nav-item'>
        <CDropdownToggle placeholder='bottom-end' className='py-0 pe-0' caret={false}>
            <CAvatar src = {avatar8} size='md'/>
        </CDropdownToggle>
        <CDropdownMenu className='pt-0'>
            <CDropdownHeader className='bg-body-secondary fw-semibold mb-2'>
                Account
            </CDropdownHeader>
            <CDropdownItem href='#' >
                 <CIcon icon={cilBell} className='me-2'/>
            </CDropdownItem>

        </CDropdownMenu>
       </CDropdown>
    )
}
export default AppHeaderDropdown