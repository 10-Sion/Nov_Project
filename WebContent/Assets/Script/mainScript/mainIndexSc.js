$(document).ready(function() {
  'use strict';

  class Menu {
    constructor(settings) {
      this.menuRootNode = settings.menuRootNode;
      this.isOpened = false;
    }

    changeMenuState(menuState) {
      return this.isOpened = !menuState;
    }
  }

  class MenuBurger extends Menu {
    constructor(settings) {
      super(settings);
      this.openText = settings.openText;
      this.closeText = settings.closeText;
      this.menuClassesNames = settings.menuClassesNames;
    }

    init() {
      let currentMenuState = this.changeMenuState(this.isOpened);
      let toggleHint = this.getCurrentToggleHint(currentMenuState, this.openText, this.closeText);
      let toggleNode = this.menuRootNode.find(`.${this.menuClassesNames.toggleClass}`);
      let menuItems = this.menuRootNode.find(`.${this.menuClassesNames.menuItemClass}`);

      this.changeToggleHint(
        toggleHint,
        this.menuRootNode.find(`.${this.menuClassesNames.toggleHintClass}`)
      );
      this.menuRootNode.toggleClass(`${this.menuClassesNames.activeClass}`);
      this.setCurrentA11yAttribute(currentMenuState, toggleNode, "aria-expanded");
      this.getFocusCurrentNode(currentMenuState, toggleNode, menuItems.eq(0));
    }

    changeToggleHint(toggleHint, toggleNode) {
      toggleNode.text(toggleHint);
      return toggleHint;
    }

    getCurrentToggleHint(currentMenuState, openText, closeText) {
      return (currentMenuState !== true) ? openText : closeText;
    }

    setCurrentA11yAttribute(currentMenuState, toggleNode, attribute) {
      return toggleNode.attr(attribute, currentMenuState);
    }

    getFocusCurrentNode(currentMenuState, nodeFirst, nodeSecond) {
      return (currentMenuState !== true) ? this.setFocus(nodeFirst) : this.setFocus(nodeSecond);
    }

    setFocus(node) {
      return node.focus();
    }
  }

  const menuClassesNames = {
    rootClass: 'menu',
    activeClass: 'menu_activated',
    toggleClass: 'menu__toggle',
    toggleHintClass: 'menu__toggle-hint',
    menuItemClass: 'menu__link'
  }

  const jsMenuNode = $(`.${menuClassesNames.rootClass}`);
  const demoMenu = new MenuBurger({
    menuRootNode: jsMenuNode,
    menuClassesNames: menuClassesNames,
    openText: 'Open menu',
    closeText: 'Close menu'
  });

  function toggleMenu(event) {
    return demoMenu.init();
  }

  jsMenuNode.find(`.${menuClassesNames.toggleClass}`).click(toggleMenu);
});

//	메뉴 내 세부 메뉴 처리
$('.menu__group').click(function() {
	$('.menu_subGroup').hide();	//	서브그룹 숨기기
	
    var menuGroup = $(this);
    // 클릭한 메뉴 그룹에 대한 서브 그룹 요소.
    var subGroup = menuGroup.find('.menu_subGroup');

    subGroup.toggle(); // show 클래스 toggle 처리 -> 애니메이션 처리함
});

