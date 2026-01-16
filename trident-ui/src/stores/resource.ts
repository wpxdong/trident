import { ref } from 'vue'
import { defineStore } from 'pinia'
// import Axios from 'axios'
import _ from 'lodash'

// let baseUrl = 'http://localhost:8017/portal'
// if (process.env.NODE_ENV === 'production') {
//   baseUrl = '/portal'
// }
// const employeeId = ''
// const AdminToken = 'access_token'
import type { Menu } from '@/vo/Layout'
import { i18n } from '../i18n'
export const useResourceStore = defineStore(' resource', () => {
  const menus = ref<Menu[]>([
    {
      id: '1',
      name: i18n.global.t('M_BREAD_CRUMB_0001'),
      isDir: false,
      link: '/portal/home',
      iconClass: 'fas fa-home',
    },
    {
      id: '2',
      name: i18n.global.t('M_MENU_0000'),
      iconClass: 'far fa-chart-bar',
      isDir: true,
      isOpen: false,
      children: [
        { id: '21', name: i18n.global.t('M_MENU_0001'), isDir: false, link: '/portal/user/list' },
      ],
    },
    {
      id: '3',
      name: i18n.global.t('M_MENU_0002'),
      iconClass: 'fas fa-address-card',
      isDir: true,
      isOpen: false,
      children: [
        { id: '31', name: i18n.global.t('M_MENU_0003'), isDir: false, link: '/portal/app/config' },
      ],
    },
    {
      id: '4',
      name: i18n.global.t('M_MENU_0004'),
      iconClass: 'fas fa-award',
      isDir: true,
      isOpen: false,
      children: [
        { id: '41', name: i18n.global.t('M_MENU_0005'), isDir: false, link: '/portal/right/role' },
      ],
    },
    {
      id: '5',
      name: i18n.global.t('M_MENU_0006'),
      iconClass: 'fas fa-award',
      isDir: true,
      isOpen: false,
      children: [
        { id: '41', name: i18n.global.t('M_MENU_0007'), isDir: false, link: '/portal/log/syslog' },
      ],
    },
  ])
  // const role = ref('admin')
  const getResouces = () => {
    // const res = await Axios.get(baseUrl + '/resources', {
    //   params: {
    //     employeeId,
    //   },
    // })
    // menus.value = res.data
    return menus.value
  }

  return { getResouces }
})
