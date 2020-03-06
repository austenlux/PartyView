# PartyView
Turn any Android View into a Party!


**PartyImageView** and **PartyTextView** are provided, but any Android View can easily be partyfied by implementing the **PartyView** interface.

## Usage:

### PartyImageView

![PartyImageView](partyview/src/main/assets/demo/PartyImageView.gif)

```
<com.lux.partyview.PartyImageView
    android:id="@+id/partyImage"
    android:layout_width="80dp"
    android:layout_height="80dp"
    android:src="@drawable/wt"
    app:partyImageView_colorStep="0.2"
    app:partyImageView_colors="@array/party"
    app:partyImageView_mode="on"
    app:partyImageView_radianStep="0.2"
    app:partyImageView_radius="30"
    app:partyImageView_reverse="false" />
```

***

### PartyTextView

![PartyImageView](partyview/src/main/assets/demo/PartyTextView.gif)

```
<com.lux.partyview.PartyTextView
    android:id="@+id/partyText"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="WillowTree"
    android:textColor="@android:color/black"
    android:textSize="60sp"
    app:partyTextView_colorStep="0.2"
    app:partyTextView_colors="@array/party"
    app:partyTextView_mode="tap_on_off"
    app:partyTextView_radianStep="0.2"
    app:partyTextView_radius="30"
    app:partyTextView_reverse="false" />
```

## Custom Attributes:

#### Colors:
IntArray of colors that the PartyView will rotate through.

Example:
First define the array of colors in `colors.xml` like this:

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="color_0">#ff8d8b</color>
    <color name="color_1">#fed689</color>
    <color name="color_2">#88ff89</color>
    <color name="color_3">#87ffff</color>
    <color name="color_4">#8bb5fe</color>
    <color name="color_5">#d78cff</color>
    <color name="color_6">#ff8cff</color>
    <color name="color_7">#ff68f7</color>
    <color name="color_8">#fe6cb7</color>
    <color name="color_9">#ff6868</color>

    <array name="party">
        <item>@color/color_0</item>
        <item>@color/color_1</item>
        <item>@color/color_2</item>
        <item>@color/color_3</item>
        <item>@color/color_4</item>
        <item>@color/color_5</item>
        <item>@color/color_6</item>
        <item>@color/color_7</item>
        <item>@color/color_8</item>
        <item>@color/color_9</item>
    </array>
</resources>
```

Then use the array of colors in the PartyView like this:

**XML:** 
```app:partyImageView_colors="@array/party"```

**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).colors = resources.getIntArray(R.array.party)```

***
#### Color Step:
Float that controls how fast the PartyView will change colors. The higher the number the faster it changes colors. Default value = 0.2f

Example:

**XML:**
```app:partyImageView_colorStep="0.2"```

**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).colorStep = 0.2f```


***
#### Radius:
Int (in pixels) that controls how large of a circle the PartyView will travel along. The higher the number the larger the circle. Default value = 30

Example:

**XML:**
```app:partyImageView_radius="30"```

**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).radius = 30```


***
#### Radian Step:
Float (in radians) that controls how fast the PartyView will travel around the circle. The higher the number the faster the PartyView will travel around the circle. Default value = 0.2f

Example:

**XML:**
```app:partyImageView_radianStep="0.2"```

**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).radianStep = 0.2f```


***
#### Reverse:
Boolean that controls if the PartyView travels in a clockwise or counter clockwise circle.

|Value|Behavior|
|:-:|:-|
FALSE|Clockwise. This is the Default Value
TRUE|Counter Clockwise

Example:

**XML:**
```app:partyImageView_reverse="true"```


**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).reverse = true```


***
#### Mode:
Enum that controls what mode the PartyView will operate in. Can be assigned to a value from the PartyView.PartyMode enum.

| Mode | Effect |
|:-:|:-|
ON|The PartyView is partying. It will move in a circle and change colors. This is the Default Value.
OFF|The PartyView is not partying. It will not move or change colors.
SPIN|The PartyView will only move in a circle. It will not change colors.
STROBE|The PartyView will only change colors. It will not move in a circle.
TAP_ON_OFF|The PartyView can be turned ON and OFF by tapping on the view.
TAP_SPIN|The PartyView's ability to move in a circle can be turned ON and OFF by tapping on the view. It will not change colors.
TAP_STROBE|The PartyView's ability to change colors can be turned ON and OFF by tapping on the view. It will not move in a circle.
TAP_THRU|Tapping the PartyView once will make it change colors. Tapping it again will make it move in a circle and change colors. Tapping it again will turn OFF moving in a circle and changing colors.

Example:

**XML:**
```app:partyTextView_mode="on"```

**Kotlin:**
```findViewById<PartyImageView>(R.id.partyImage).mode = PartyView.PartyMode.ON```
