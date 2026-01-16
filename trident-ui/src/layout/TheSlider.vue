<template>
  <div class="navigator">
    <div class="nav-header">
      <div class="header-info">
        <h2>Trident</h2>
      </div>
      <span class="toggle-btn">
        <i class="fa fa-bars"></i>
      </span>
    </div>
    <div class="menu">
      <el-menu class="el-menu-vertical-demo">
        <template v-for="(item, index) in permisMenu" :key="index">
          <SlideItem :item="item"></SlideItem>
        </template>
      </el-menu>
    </div>
  </div>
</template>
<script lang="ts" setup>
import SlideItem from './SlideItem.vue'
import { useResourceStore } from '@/stores/resource'
import type { Menu } from '@/vo/Layout'
import { onBeforeMount } from 'vue'
const store = useResourceStore()

let permisMenu: Array<Menu>
onBeforeMount(() => {
  permisMenu = store.getResouces()
})
</script>
<style lang="scss" scoped>
@use 'scss/_index.scss' as *;
.navigator {
  height: 100%;
  .nav-header {
    height: 60px;
    padding-left: 20px;
    border-bottom: 1px solid $CUSTOM_BORDER_COLOR;
    .header-info {
      &:hover {
        color: $THEME_COLOR;
        cursor: pointer;
      }
      h2 {
        font-weight: bolder;
        font-size: 30px;
        padding-top: 14px;
        line-height: 32px;
        letter-spacing: 2px;
      }
      p {
        font-size: 14px;
        line-height: 14px;
        // letter-spacing: 0.5px;
      }
    }
    .toggle-btn {
      position: absolute;
      top: 0;
      right: 0;
      display: inline-block;
      line-height: 60px;
      padding: 0 20px;
      &:hover {
        cursor: pointer;
        background: $HOVER_BACKGROUND_COLOR;
        .fa-bars:before {
          color: $THEME_COLOR;
        }
      }
    }
  }
  .menu {
    height: calc(100% - 60px);
    overflow: auto;
    .el-menu {
      border-right: none;
      i {
        font-size: 16px;
        width: 24px;
        margin-right: 5px;
        text-align: center;
      }
      .el-menu-item {
        line-height: 48px;
        height: 48px;
      }
      .el-submenu__title {
        line-height: 48px;
        height: 48px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }
}
</style>
