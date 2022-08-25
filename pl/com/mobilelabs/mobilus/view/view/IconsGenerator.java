package pl.com.mobilelabs.mobilus.view.view;

import pl.com.mobilelabs.mobilus.model.objects.BaseObject;

public class IconsGenerator {
  public static int getErrorImageResource(BaseObject paramBaseObject, boolean paramBoolean) {
    if (paramBaseObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
      switch (paramBaseObject.getIcon()) {
        default:
          return 2131558700;
        case 10:
          return 2131558827;
        case 9:
          return 2131558761;
        case 8:
          return 2131558730;
        case 7:
          return 2131558640;
        case 6:
          return 2131558875;
        case 5:
          return 2131558858;
        case 4:
          return 2131558455;
        case 3:
          return 2131558552;
        case 2:
          break;
      } 
      return 2131558503;
    } 
    if (paramBoolean) {
      switch (paramBaseObject.getIcon()) {
        default:
          return 2131558702;
        case 35:
          return 2131558843;
        case 34:
          return 2131558763;
        case 33:
          return 2131558732;
        case 32:
          return 2131558642;
        case 31:
          return 2131558890;
        case 30:
          return 2131558802;
        case 29:
          return 2131558780;
        case 28:
          return 2131558785;
        case 27:
          return 2131558671;
        case 26:
          return 2131558611;
        case 25:
          return 2131558666;
        case 24:
          return 2131558648;
        case 23:
          return 2131558653;
        case 22:
          return 2131558598;
        case 21:
          return 2131558585;
        case 20:
          return 2131558559;
        case 19:
          return 2131558885;
        case 18:
          return 2131558881;
        case 17:
          return 2131558794;
        case 16:
          return 2131558661;
        case 15:
          return 2131558615;
        case 14:
          return 2131558606;
        case 13:
          return 2131558602;
        case 12:
          return 2131558593;
        case 11:
          return 2131558589;
        case 10:
          return 2131558580;
        case 9:
          return 2131558571;
        case 8:
          return 2131558563;
        case 7:
          return 2131558477;
        case 6:
          return 2131558877;
        case 5:
          return 2131558860;
        case 4:
          return 2131558457;
        case 3:
          return 2131558554;
        case 2:
          break;
      } 
      return 2131558505;
    } 
    switch (paramBaseObject.getIcon()) {
      default:
        return 2131558687;
      case 35:
        return 2131558814;
      case 34:
        return 2131558748;
      case 33:
        return 2131558717;
      case 32:
        return 2131558627;
      case 31:
        return 2131558886;
      case 30:
        return 2131558798;
      case 29:
        return 2131558776;
      case 28:
        return 2131558781;
      case 27:
        return 2131558667;
      case 26:
        return 2131558607;
      case 25:
        return 2131558662;
      case 24:
        return 2131558644;
      case 23:
        return 2131558649;
      case 22:
        return 2131558594;
      case 21:
        return 2131558581;
      case 20:
        return 2131558555;
      case 19:
        return 2131558882;
      case 18:
        return 2131558878;
      case 17:
        return 2131558791;
      case 16:
        return 2131558658;
      case 15:
        return 2131558612;
      case 14:
        return 2131558603;
      case 13:
        return 2131558599;
      case 12:
        return 2131558590;
      case 11:
        return 2131558586;
      case 10:
        return 2131558577;
      case 9:
        return 2131558568;
      case 8:
        return 2131558560;
      case 7:
        return 2131558474;
      case 6:
        return 2131558872;
      case 5:
        return 2131558855;
      case 4:
        return 2131558441;
      case 3:
        return 2131558539;
      case 2:
        break;
    } 
    return 2131558489;
  }
  
  public static int getLeanErrorImageResource(boolean paramBoolean) {
    return paramBoolean ? 2131558842 : 2131558840;
  }
  
  public static int getLeanImageResource(int paramInt) {
    switch ((int)Math.round(paramInt / 10.0D)) {
      default:
        return 2131558829;
      case 10:
        return 2131558831;
      case 9:
        return 2131558839;
      case 8:
        return 2131558838;
      case 7:
        return 2131558837;
      case 6:
        return 2131558836;
      case 5:
        return 2131558835;
      case 4:
        return 2131558834;
      case 3:
        return 2131558833;
      case 2:
        return 2131558832;
      case 1:
        break;
    } 
    return 2131558830;
  }
  
  public static int getPositionImageResource(BaseObject paramBaseObject, int paramInt) {
    paramInt = (int)Math.round(paramInt / 10.0D);
    if (paramBaseObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
      switch (paramBaseObject.getIcon()) {
        default:
          switch (paramInt) {
            default:
              return 2131558689;
            case 10:
              return 2131558691;
            case 9:
              return 2131558699;
            case 8:
              return 2131558698;
            case 7:
              return 2131558697;
            case 6:
              return 2131558696;
            case 5:
              return 2131558695;
            case 4:
              return 2131558694;
            case 3:
              return 2131558693;
            case 2:
              return 2131558692;
            case 1:
              break;
          } 
          return 2131558690;
        case 10:
          switch (paramInt) {
            default:
              return 2131558816;
            case 10:
              return 2131558818;
            case 9:
              return 2131558826;
            case 8:
              return 2131558825;
            case 7:
              return 2131558824;
            case 6:
              return 2131558823;
            case 5:
              return 2131558822;
            case 4:
              return 2131558821;
            case 3:
              return 2131558820;
            case 2:
              return 2131558819;
            case 1:
              break;
          } 
          return 2131558817;
        case 9:
          switch (paramInt) {
            default:
              return 2131558750;
            case 10:
              return 2131558752;
            case 9:
              return 2131558760;
            case 8:
              return 2131558759;
            case 7:
              return 2131558758;
            case 6:
              return 2131558757;
            case 5:
              return 2131558756;
            case 4:
              return 2131558755;
            case 3:
              return 2131558754;
            case 2:
              return 2131558753;
            case 1:
              break;
          } 
          return 2131558751;
        case 8:
          switch (paramInt) {
            default:
              return 2131558719;
            case 10:
              return 2131558721;
            case 9:
              return 2131558729;
            case 8:
              return 2131558728;
            case 7:
              return 2131558727;
            case 6:
              return 2131558726;
            case 5:
              return 2131558725;
            case 4:
              return 2131558724;
            case 3:
              return 2131558723;
            case 2:
              return 2131558722;
            case 1:
              break;
          } 
          return 2131558720;
        case 7:
          switch (paramInt) {
            default:
              return 2131558629;
            case 10:
              return 2131558631;
            case 9:
              return 2131558639;
            case 8:
              return 2131558638;
            case 7:
              return 2131558637;
            case 6:
              return 2131558636;
            case 5:
              return 2131558635;
            case 4:
              return 2131558634;
            case 3:
              return 2131558633;
            case 2:
              return 2131558632;
            case 1:
              break;
          } 
          return 2131558630;
        case 6:
          return (paramInt > 5) ? 2131558874 : 2131558876;
        case 5:
          return (paramInt > 5) ? 2131558857 : 2131558859;
        case 4:
          switch (paramInt) {
            default:
              return 2131558444;
            case 10:
              return 2131558446;
            case 9:
              return 2131558454;
            case 8:
              return 2131558453;
            case 7:
              return 2131558452;
            case 6:
              return 2131558451;
            case 5:
              return 2131558450;
            case 4:
              return 2131558449;
            case 3:
              return 2131558448;
            case 2:
              return 2131558447;
            case 1:
              break;
          } 
          return 2131558445;
        case 3:
          switch (paramInt) {
            default:
              return 2131558541;
            case 10:
              return 2131558543;
            case 9:
              return 2131558551;
            case 8:
              return 2131558550;
            case 7:
              return 2131558549;
            case 6:
              return 2131558548;
            case 5:
              return 2131558547;
            case 4:
              return 2131558546;
            case 3:
              return 2131558545;
            case 2:
              return 2131558544;
            case 1:
              break;
          } 
          return 2131558542;
        case 2:
          break;
      } 
      switch (paramInt) {
        default:
          return 2131558492;
        case 10:
          return 2131558494;
        case 9:
          return 2131558502;
        case 8:
          return 2131558501;
        case 7:
          return 2131558500;
        case 6:
          return 2131558499;
        case 5:
          return 2131558498;
        case 4:
          return 2131558497;
        case 3:
          return 2131558496;
        case 2:
          return 2131558495;
        case 1:
          break;
      } 
      return 2131558493;
    } 
    switch (paramBaseObject.getIcon()) {
      default:
        switch (paramInt) {
          default:
            return 2131558676;
          case 10:
            return 2131558678;
          case 9:
            return 2131558686;
          case 8:
            return 2131558685;
          case 7:
            return 2131558684;
          case 6:
            return 2131558683;
          case 5:
            return 2131558682;
          case 4:
            return 2131558681;
          case 3:
            return 2131558680;
          case 2:
            return 2131558679;
          case 1:
            break;
        } 
        return 2131558677;
      case 35:
        switch (paramInt) {
          default:
            return 2131558803;
          case 10:
            return 2131558805;
          case 9:
            return 2131558813;
          case 8:
            return 2131558812;
          case 7:
            return 2131558811;
          case 6:
            return 2131558810;
          case 5:
            return 2131558809;
          case 4:
            return 2131558808;
          case 3:
            return 2131558807;
          case 2:
            return 2131558806;
          case 1:
            break;
        } 
        return 2131558804;
      case 34:
        switch (paramInt) {
          default:
            return 2131558737;
          case 10:
            return 2131558739;
          case 9:
            return 2131558747;
          case 8:
            return 2131558746;
          case 7:
            return 2131558745;
          case 6:
            return 2131558744;
          case 5:
            return 2131558743;
          case 4:
            return 2131558742;
          case 3:
            return 2131558741;
          case 2:
            return 2131558740;
          case 1:
            break;
        } 
        return 2131558738;
      case 33:
        switch (paramInt) {
          default:
            return 2131558706;
          case 10:
            return 2131558708;
          case 9:
            return 2131558716;
          case 8:
            return 2131558715;
          case 7:
            return 2131558714;
          case 6:
            return 2131558713;
          case 5:
            return 2131558712;
          case 4:
            return 2131558711;
          case 3:
            return 2131558710;
          case 2:
            return 2131558709;
          case 1:
            break;
        } 
        return 2131558707;
      case 32:
        switch (paramInt) {
          default:
            return 2131558616;
          case 10:
            return 2131558618;
          case 9:
            return 2131558626;
          case 8:
            return 2131558625;
          case 7:
            return 2131558624;
          case 6:
            return 2131558623;
          case 5:
            return 2131558622;
          case 4:
            return 2131558621;
          case 3:
            return 2131558620;
          case 2:
            return 2131558619;
          case 1:
            break;
        } 
        return 2131558617;
      case 31:
        return (paramInt > 5) ? 2131558889 : 2131558888;
      case 30:
        return (paramInt > 5) ? 2131558801 : 2131558800;
      case 29:
        return (paramInt > 5) ? 2131558779 : 2131558778;
      case 28:
        return (paramInt > 5) ? 2131558784 : 2131558783;
      case 27:
        return (paramInt > 5) ? 2131558670 : 2131558669;
      case 26:
        return (paramInt > 5) ? 2131558610 : 2131558609;
      case 25:
        return (paramInt > 5) ? 2131558665 : 2131558664;
      case 24:
        return (paramInt > 5) ? 2131558647 : 2131558646;
      case 23:
        return (paramInt > 5) ? 2131558652 : 2131558651;
      case 22:
        return (paramInt > 5) ? 2131558597 : 2131558596;
      case 21:
        return (paramInt > 5) ? 2131558584 : 2131558583;
      case 20:
        return (paramInt > 5) ? 2131558558 : 2131558557;
      case 19:
        return (paramInt > 5) ? 2131558884 : 2131558883;
      case 18:
        return (paramInt > 5) ? 2131558880 : 2131558879;
      case 17:
        return (paramInt > 5) ? 2131558793 : 2131558792;
      case 16:
        return (paramInt > 5) ? 2131558660 : 2131558659;
      case 15:
        return (paramInt > 5) ? 2131558614 : 2131558613;
      case 14:
        return (paramInt > 5) ? 2131558605 : 2131558604;
      case 13:
        return (paramInt > 5) ? 2131558601 : 2131558600;
      case 12:
        return (paramInt > 5) ? 2131558592 : 2131558591;
      case 11:
        return (paramInt > 5) ? 2131558588 : 2131558587;
      case 10:
        return (paramInt > 5) ? 2131558579 : 2131558578;
      case 9:
        return (paramInt > 5) ? 2131558570 : 2131558569;
      case 8:
        return (paramInt > 5) ? 2131558562 : 2131558561;
      case 7:
        return (paramInt > 5) ? 2131558476 : 2131558475;
      case 6:
        switch (paramInt) {
          default:
            return 2131558861;
          case 10:
            return 2131558863;
          case 9:
            return 2131558871;
          case 8:
            return 2131558870;
          case 7:
            return 2131558869;
          case 6:
            return 2131558868;
          case 5:
            return 2131558867;
          case 4:
            return 2131558866;
          case 3:
            return 2131558865;
          case 2:
            return 2131558864;
          case 1:
            break;
        } 
        return 2131558862;
      case 5:
        switch (paramInt) {
          default:
            return 2131558844;
          case 10:
            return 2131558846;
          case 9:
            return 2131558854;
          case 8:
            return 2131558853;
          case 7:
            return 2131558852;
          case 6:
            return 2131558851;
          case 5:
            return 2131558850;
          case 4:
            return 2131558849;
          case 3:
            return 2131558848;
          case 2:
            return 2131558847;
          case 1:
            break;
        } 
        return 2131558845;
      case 4:
        switch (paramInt) {
          default:
            return 2131558430;
          case 10:
            return 2131558432;
          case 9:
            return 2131558440;
          case 8:
            return 2131558439;
          case 7:
            return 2131558438;
          case 6:
            return 2131558437;
          case 5:
            return 2131558436;
          case 4:
            return 2131558435;
          case 3:
            return 2131558434;
          case 2:
            return 2131558433;
          case 1:
            break;
        } 
        return 2131558431;
      case 3:
        switch (paramInt) {
          default:
            return 2131558528;
          case 10:
            return 2131558530;
          case 9:
            return 2131558538;
          case 8:
            return 2131558537;
          case 7:
            return 2131558536;
          case 6:
            return 2131558535;
          case 5:
            return 2131558534;
          case 4:
            return 2131558533;
          case 3:
            return 2131558532;
          case 2:
            return 2131558531;
          case 1:
            break;
        } 
        return 2131558529;
      case 2:
        break;
    } 
    switch (paramInt) {
      default:
        return 2131558478;
      case 10:
        return 2131558480;
      case 9:
        return 2131558488;
      case 8:
        return 2131558487;
      case 7:
        return 2131558486;
      case 6:
        return 2131558485;
      case 5:
        return 2131558484;
      case 4:
        return 2131558483;
      case 3:
        return 2131558482;
      case 2:
        return 2131558481;
      case 1:
        break;
    } 
    return 2131558479;
  }
  
  public static int getSelectionImageResource(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramBoolean2) {
      switch (paramInt) {
        default:
          return paramBoolean1 ? 2131558695 : 2131558701;
        case 10:
          return paramBoolean1 ? 2131558822 : 2131558828;
        case 9:
          return paramBoolean1 ? 2131558756 : 2131558762;
        case 8:
          return paramBoolean1 ? 2131558725 : 2131558731;
        case 7:
          return paramBoolean1 ? 2131558635 : 2131558641;
        case 6:
          return paramBoolean1 ? 2131558874 : 2131558876;
        case 5:
          return paramBoolean1 ? 2131558857 : 2131558859;
        case 4:
          return paramBoolean1 ? 2131558450 : 2131558456;
        case 3:
          return paramBoolean1 ? 2131558547 : 2131558553;
        case 2:
          break;
      } 
      return paramBoolean1 ? 2131558498 : 2131558504;
    } 
    switch (paramInt) {
      default:
        return paramBoolean1 ? 2131558682 : 2131558688;
      case 35:
        return paramBoolean1 ? 2131558809 : 2131558815;
      case 34:
        return paramBoolean1 ? 2131558743 : 2131558749;
      case 33:
        return paramBoolean1 ? 2131558712 : 2131558718;
      case 32:
        return paramBoolean1 ? 2131558622 : 2131558628;
      case 31:
        return paramBoolean1 ? 2131558889 : 2131558888;
      case 30:
        return paramBoolean1 ? 2131558801 : 2131558800;
      case 29:
        return paramBoolean1 ? 2131558779 : 2131558778;
      case 28:
        return paramBoolean1 ? 2131558784 : 2131558783;
      case 27:
        return paramBoolean1 ? 2131558670 : 2131558669;
      case 26:
        return paramBoolean1 ? 2131558610 : 2131558609;
      case 25:
        return paramBoolean1 ? 2131558665 : 2131558664;
      case 24:
        return paramBoolean1 ? 2131558647 : 2131558646;
      case 23:
        return paramBoolean1 ? 2131558652 : 2131558651;
      case 22:
        return paramBoolean1 ? 2131558597 : 2131558596;
      case 21:
        return paramBoolean1 ? 2131558584 : 2131558583;
      case 20:
        return paramBoolean1 ? 2131558558 : 2131558557;
      case 19:
        return paramBoolean1 ? 2131558884 : 2131558883;
      case 18:
        return paramBoolean1 ? 2131558880 : 2131558879;
      case 17:
        return paramBoolean1 ? 2131558793 : 2131558792;
      case 16:
        return paramBoolean1 ? 2131558660 : 2131558659;
      case 15:
        return paramBoolean1 ? 2131558614 : 2131558613;
      case 14:
        return paramBoolean1 ? 2131558605 : 2131558604;
      case 13:
        return paramBoolean1 ? 2131558601 : 2131558600;
      case 12:
        return paramBoolean1 ? 2131558592 : 2131558591;
      case 11:
        return paramBoolean1 ? 2131558588 : 2131558587;
      case 10:
        return paramBoolean1 ? 2131558579 : 2131558578;
      case 9:
        return paramBoolean1 ? 2131558570 : 2131558569;
      case 8:
        return paramBoolean1 ? 2131558562 : 2131558561;
      case 7:
        return paramBoolean1 ? 2131558476 : 2131558475;
      case 6:
        return paramBoolean1 ? 2131558861 : 2131558873;
      case 5:
        return paramBoolean1 ? 2131558844 : 2131558856;
      case 4:
        return paramBoolean1 ? 2131558436 : 2131558442;
      case 3:
        return paramBoolean1 ? 2131558534 : 2131558540;
      case 2:
        break;
    } 
    return paramBoolean1 ? 2131558484 : 2131558490;
  }
  
  public static int getTotallyClosedImageResource(BaseObject paramBaseObject) {
    if (paramBaseObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
      switch (paramBaseObject.getIcon()) {
        default:
          return 2131558689;
        case 10:
          return 2131558816;
        case 9:
          return 2131558750;
        case 8:
          return 2131558719;
        case 7:
          return 2131558629;
        case 6:
          return 2131558876;
        case 5:
          return 2131558859;
        case 4:
          return 2131558444;
        case 3:
          return 2131558541;
        case 2:
          break;
      } 
      return 2131558492;
    } 
    switch (paramBaseObject.getIcon()) {
      default:
        return 2131558676;
      case 35:
        return 2131558803;
      case 34:
        return 2131558737;
      case 33:
        return 2131558706;
      case 32:
        return 2131558616;
      case 31:
        return 2131558888;
      case 30:
        return 2131558800;
      case 29:
        return 2131558778;
      case 28:
        return 2131558783;
      case 27:
        return 2131558669;
      case 26:
        return 2131558609;
      case 25:
        return 2131558664;
      case 24:
        return 2131558646;
      case 23:
        return 2131558651;
      case 22:
        return 2131558596;
      case 21:
        return 2131558583;
      case 20:
        return 2131558557;
      case 19:
        return 2131558883;
      case 18:
        return 2131558879;
      case 17:
        return 2131558792;
      case 16:
        return 2131558659;
      case 15:
        return 2131558613;
      case 14:
        return 2131558604;
      case 13:
        return 2131558600;
      case 12:
        return 2131558591;
      case 11:
        return 2131558587;
      case 10:
        return 2131558578;
      case 9:
        return 2131558569;
      case 8:
        return 2131558561;
      case 7:
        return 2131558475;
      case 6:
        return 2131558863;
      case 5:
        return 2131558846;
      case 4:
        return 2131558430;
      case 3:
        return 2131558528;
      case 2:
        break;
    } 
    return 2131558478;
  }
  
  public static int getTotallyOpenedImageResource(BaseObject paramBaseObject) {
    if (paramBaseObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
      switch (paramBaseObject.getIcon()) {
        default:
          return 2131558691;
        case 10:
          return 2131558818;
        case 9:
          return 2131558752;
        case 8:
          return 2131558721;
        case 7:
          return 2131558631;
        case 6:
          return 2131558874;
        case 5:
          return 2131558857;
        case 4:
          return 2131558446;
        case 3:
          return 2131558543;
        case 2:
          break;
      } 
      return 2131558494;
    } 
    switch (paramBaseObject.getIcon()) {
      default:
        return 2131558678;
      case 35:
        return 2131558805;
      case 34:
        return 2131558739;
      case 33:
        return 2131558708;
      case 32:
        return 2131558618;
      case 31:
        return 2131558889;
      case 30:
        return 2131558801;
      case 29:
        return 2131558779;
      case 28:
        return 2131558784;
      case 27:
        return 2131558670;
      case 26:
        return 2131558610;
      case 25:
        return 2131558665;
      case 24:
        return 2131558647;
      case 23:
        return 2131558652;
      case 22:
        return 2131558597;
      case 21:
        return 2131558584;
      case 20:
        return 2131558558;
      case 19:
        return 2131558884;
      case 18:
        return 2131558880;
      case 17:
        return 2131558793;
      case 16:
        return 2131558660;
      case 15:
        return 2131558614;
      case 14:
        return 2131558605;
      case 13:
        return 2131558601;
      case 12:
        return 2131558592;
      case 11:
        return 2131558588;
      case 10:
        return 2131558579;
      case 9:
        return 2131558570;
      case 8:
        return 2131558562;
      case 7:
        return 2131558476;
      case 6:
        return 2131558861;
      case 5:
        return 2131558844;
      case 4:
        return 2131558432;
      case 3:
        return 2131558530;
      case 2:
        break;
    } 
    return 2131558480;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\IconsGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */