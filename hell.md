```
this is some code
[not a link](/fakelink)
```

``[NOt link]()``


``
[also `not` a ```link```]()
``

`[potential`](link)

**__[link__**](sdf)

I could include links that aren't links because of indentation and code-block styling, but I don't hate myself that much
- ex:
    
	there is a tab before this line, extending the previous bullet

	- this is a sub bullet
		
		there are two tabs before this one, 

				there are four tabs in front of this but now it's a code block. If i put a link here, it wouldn't link. 
		the thing is that the amount of whitespace is nebulous, meaning that your indentation can be all over the place and therefore a regex trying to parse it will implode.