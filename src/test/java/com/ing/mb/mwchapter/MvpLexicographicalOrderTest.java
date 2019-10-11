package com.ing.mb.mwchapter;

class MvpLexicographicalOrderTest extends LexicographicalOrderAbstractTest {

  @Override
  public LexicographicalOrder getSut() {
    return new LexicographicalOrderMvpImpl();
  }

}